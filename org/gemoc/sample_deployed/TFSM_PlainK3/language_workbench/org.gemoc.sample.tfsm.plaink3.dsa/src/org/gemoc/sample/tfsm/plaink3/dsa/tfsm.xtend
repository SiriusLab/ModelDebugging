package org.gemoc.sample.tfsm.plaink3.dsa

import fr.inria.diverse.k3.al.annotationprocessor.Aspect
import fr.inria.diverse.k3.al.annotationprocessor.TransactionSupport
import org.gemoc.sample.tfsm.FSMClock
import org.gemoc.sample.tfsm.FSMEvent
import org.gemoc.sample.tfsm.State
import org.gemoc.sample.tfsm.TFSM
import org.gemoc.sample.tfsm.Transition

import static extension fr.inria.diverse.k3.al.annotationprocessor.TransactionSupport.*
import static extension org.gemoc.sample.tfsm.plaink3.dsa.FSMClockAspect.*
import static extension org.gemoc.sample.tfsm.plaink3.dsa.TFSMAspect.*

@Aspect(className=TFSM)
class TFSMAspect
{

	// should be added automatically by the dsa, currently introduced directly in the ecore
	// public State currentState;
	public State currentState

	def public void init()
	{
		if(_self.currentState == null)
		{
			_self.currentState = _self.initialState;
		}

		for (o : _self.eAllContents.toSet)
		{
			if(o instanceof FSMClock)
			{
				o.numberOfTicks = 0
			}
		}

		println("[" + _self.getClass().getSimpleName() + ":" + _self.getName() + ".Init()]Initialized " + _self.name)
	}

}

@Aspect(className=FSMClock, transactionSupport=TransactionSupport.EMF)
class FSMClockAspect
{
	public Integer numberOfTicks = 0;

	// Clock tick
	def public Integer ticks()
	{
		_self.numberOfTicks = _self.numberOfTicks + 1
		println(
			"[" + _self.getClass().getSimpleName() + ":" + _self.getName() + ".ticks()]New number of ticks : " +
				_self.numberOfTicks.toString)
		return _self.numberOfTicks
	}

}

@Aspect(className=State)
class StateAspect
{
	def public void onEnter()
	{
		println("[" + _self.getClass().getSimpleName() + ":" + _self.getName() + ".onEnter()]Entering " + _self.name)
	}

	def public void onLeave()
	{
		println("[" + _self.getClass().getSimpleName() + ":" + _self.getName() + ".onLeave()]Leaving " + _self.name)
	}
}

@Aspect(className=Transition, transactionSupport=TransactionSupport.EMF)
class TransitionAspect
{
	def public void fire()
	{
		_self.source.owningFSM.currentState = _self.target
		println(
			"[" + _self.getClass().getSimpleName() + ":" + _self.getName() + ".fire()]Fired " + _self.name + " -> " +
				_self.action)
	}
}

@Aspect(className=FSMEvent, transactionSupport=TransactionSupport.EMF)
class FSMEventAspect
{

	public boolean isTriggered = false

	def public void trigger()
	{
		_self.isTriggered = true
	}

	def public void unTrigger()
	{
		_self.isTriggered = false
	}

}
