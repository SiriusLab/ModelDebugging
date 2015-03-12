package org.gemoc.sample.tfsm.plaink3.dsa

import org.gemoc.sample.tfsm.TFSM
import org.gemoc.sample.tfsm.State
import org.gemoc.sample.tfsm.Transition
import org.gemoc.sample.tfsm.TemporalGuard
import org.gemoc.sample.tfsm.EventGuard
import org.gemoc.sample.tfsm.EvaluateGuard
import org.gemoc.sample.tfsm.TimedSystem
import org.gemoc.sample.tfsm.FSMClock
import fr.inria.diverse.k3.al.annotationprocessor.Aspect
import static extension org.gemoc.sample.tfsm.plaink3.dsa.TimedSystemVisitorAspect.*
import static extension org.gemoc.sample.tfsm.plaink3.dsa.TFSMAspect.*
import static extension org.gemoc.sample.tfsm.plaink3.dsa.TFSMVisitorAspect.*
import static extension org.gemoc.sample.tfsm.plaink3.dsa.FSMEventAspect.*
import static extension org.gemoc.sample.tfsm.plaink3.dsa.FSMClockAspect.*
import static extension org.gemoc.sample.tfsm.plaink3.dsa.FSMClockVisitorAspect.*
import static extension org.gemoc.sample.tfsm.plaink3.dsa.StateAspect.*
import static extension org.gemoc.sample.tfsm.plaink3.dsa.StateVisitorAspect.*
import static extension org.gemoc.sample.tfsm.plaink3.dsa.TransitionAspect.*
import static extension org.gemoc.sample.tfsm.plaink3.dsa.TransitionVisitorAspect.*
import static extension org.gemoc.sample.tfsm.plaink3.dsa.GuardVisitorAspect.*
import static extension org.gemoc.sample.tfsm.plaink3.dsa.TemporalGuardVisitorAspect.*
import static extension org.gemoc.sample.tfsm.plaink3.dsa.EventGuardVisitorAspect.*
import fr.inria.diverse.k3.al.annotationprocessor.TransactionSupport
import org.gemoc.sample.tfsm.Guard

@Aspect(className=TimedSystem)
class TimedSystemVisitorAspect 
{
	def public void visit()
	{
 		_self.globalClocks.forEach[ e | e.visit()]
 		_self.tfsms.forEach[ t | t.visit()]
	}	
}

@Aspect(className=TFSM, transactionSupport = TransactionSupport.EMF)
class TFSMVisitorAspect 
{

	public int stepNumber = 0
	public int lastStateChangeStepNumber = 0

	def public void visit()
	{
		println()
		println("Step " + _self.stepNumber + " - current state: " + _self.getCurrentStateName())		
		if (_self.currentState == null)
		{
			_self.init()
		}
		else
		{
			if (_self.localClock != null)
			{
				_self.localClock.visit()
			}
			_self.currentState.visit()			
		}
 		_self.stepNumber = _self.stepNumber + 1
	}
		
	private def String getCurrentStateName()
	{
		var s = "null"
		if (_self.currentState != null)
		{
			s = _self.currentState.name
		}
		return s
	}
}

@Aspect(className=FSMClock)
class FSMClockVisitorAspect
{
	def public void visit()
	{
		_self.ticks
	}
}

@Aspect(className=State)
class StateVisitorAspect 
{
	def public void visit()
	{
		_self.onEnter
 		_self.outgoingTransitions.forEach[ t | t.visit()]
		_self.onLeave
	}
}

@Aspect(className=Transition, transactionSupport=TransactionSupport.EMF)
class TransitionVisitorAspect 
{	
	def public void visit()
	{
		if (_self.ownedGuard != null)
		{
	 		_self.ownedGuard.visit()
		}
	}
}

@Aspect(className=Guard)
abstract class GuardVisitorAspect 
{
	abstract def public void visit()
	
	protected def void fireTransition()
	{
		val transitionToFire = _self.eContainer as Transition
		println("fire transition " + transitionToFire.name)
		transitionToFire.fire 			
		_self.saveLastStateChangeStepNumber		
	}
	
	protected def int lastStateChangeStepNumber()
	{
		val tfsm = _self.eContainer.eContainer as TFSM
		return tfsm.lastStateChangeStepNumber
	}
	protected def void saveLastStateChangeStepNumber()
	{
		val tfsm = _self.eContainer.eContainer as TFSM
		tfsm.lastStateChangeStepNumber = tfsm.stepNumber
	}
}

@Aspect(className=TemporalGuard)
class TemporalGuardVisitorAspect extends GuardVisitorAspect 
{	
	def public void visit()
	{
 		if ((_self.afterDuration + _self.lastStateChangeStepNumber) == _self.onClock.numberOfTicks)
 		{
			_self.fireTransition()
 		}
	}
}

@Aspect(className=EventGuard)
class EventGuardVisitorAspect extends GuardVisitorAspect 
{
	def public void visit()
	{
 		if (_self.triggeringEvent.isTriggered)
 		{
			_self.fireTransition()
 		}
	}	
}