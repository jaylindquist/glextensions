package com.bitwiseor.extensions.batch

import java.io.File;

import org.springframework.batch.item.ItemProcessor

import com.bitwiseor.extensions.dsl.ExtensionFile

class FileToExtensionProcessor implements ItemProcessor<File, ExtensionFile>{

	@Override
	ExtensionFile process(File file) {
		def extension = new ExtensionFile()
		extension.init(file)
		return extension
	}

}
