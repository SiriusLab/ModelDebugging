package org.eclipse.gemoc.executionframework.test.lib

interface ILanguageWrapper {
	
	def String getEntryPoint()
	
	def String getLanguageName()

	def String getInitializationMethod()
	
}