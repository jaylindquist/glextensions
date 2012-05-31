package com.bitwiseor.extensions.batch

import java.io.File;

import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

import com.bitwiseor.extensions.dsl.ExtensionFile

@Component
class FileToExtensionProcessor implements ItemProcessor<File, ExtensionFile>{

	@Override
	ExtensionFile process(File file) {
		def extension = new ExtensionFile()
		extension.init(file)
		return extension
	}

}
