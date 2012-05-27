package com.bitwiseor.extensions.dsl

class ExtensionSection {
	def title = ''
	def contents = ''
	
	@Override
	String toString() {
		return "title: ${title}"
	}
}
