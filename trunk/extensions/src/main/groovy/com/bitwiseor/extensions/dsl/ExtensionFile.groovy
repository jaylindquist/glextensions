package com.bitwiseor.extensions.dsl

import groovy.util.logging.Slf4j

@Slf4j
class ExtensionFile {
	File file
	def sections = []
	
	def init(File file) {
		this.file = file
		def section = null
		
		this.file.eachLine { line ->
			if(isPreamble(line, section)) {
				// skip
			} else if(isSectionName(line, section)) {
				if(section?.title && !section?.contents) { // section title wrapped to next line
					section.title += " ${line.trim()}"
				} else {
					section = new ExtensionSection()
					this.sections << section
					section.title = line.trim()
					section.contents = ''
				}
			} else {
				section.contents += "${line}${System.properties['line.separator']}"
			}
		}
	}
	
	boolean isPreamble(String line, def section) {
		if(section) return false
		switch(line) {
			case ~/^$/:
			case ~/^[^\w].*$/:
			case ~/^.*XXX - Not complete yet!!!.*$/:
				return true
			default:
				return false
		}
	}
	
	boolean isSectionName(String line, def section) {
		if(!section) return true
		switch(line) {
			case ~/^.*\w+  +\w+.*$/: // table columns
				return false
			case ~/^[^\s].*/:
				return true
			default: 
				return false
		}
	}
	
	@Override
	String toString() {
		def sb = new StringBuilder()
		sb.append("File: ${file}").append(System.properties['line.separator'])
		sb.append("Sections:").append(System.properties['line.separator'])
		sections.each {
			sb.append('\t* ').append(it.title).append(System.properties['line.separator'])
		}
		return sb.toString()
	}
}
