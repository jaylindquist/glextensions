package com.bitwiseor.extensions.dsl

class ExtensionFile {
	File file
	def sections = []
	
	def init(File file) {
		this.file = file
		def section = null
		
		this.file.eachLine { line ->
			if(!section && line =~ /^[^\w]/) {
				// if a section has not started and the line does not start
				// with a char, then we are at the header and can skip
			} else if(!section || line =~ /^[A-Z]/) {
				// if we don't have a section or the line begins with a 
				// char, then we need to start a new section
				// note that lines beginning with numbers will be part of 
				// previous section
				section = new ExtensionSection()
				this.sections << section
				section.title = line.trim()
			} else {
				// this line is a part of the previous section
				section.contents << line << System.properties['line.separator']
			}
		}
	}
	
	@Override
	String toString() {
		return "{file: ${file.name}, sections: ${sections}"
	}
}
