package com.bitwiseor.extensions.batch

import java.util.List;

import org.springframework.batch.item.ItemWriter

import com.bitwiseor.extensions.dsl.ExtensionFile

class MapWriter implements ItemWriter<ExtensionFile> {
	def map = [:]
	
	@Override
	public void write(List<ExtensionFile> items)  {
		items.each { file ->
			file.sections.each { section ->
				if(!map[section.title]) {
					map[section.title] = 0
				}
				map[section.title]++
			}
		}
		println map
	}

}
