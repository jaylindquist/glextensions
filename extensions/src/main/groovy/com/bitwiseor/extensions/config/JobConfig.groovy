package com.bitwiseor.extensions.config

import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration;

import com.bitwiseor.extensions.batch.*

@Configuration
class JobConfig {
	@Bean
	ItemReader directoryItemReader() {
		def reader = new DirectoryItemReader()
		reader.directory = new File('D:/glextensions')
		
		return reader
	}
	
	@Bean
	ItemWriter loggingWriter() {
		return new LoggingWriter()
	}
	/*
	@Bean
	ItemWriter mapWriter() {
		return new MapWriter()
	}
	
	@Bean
	ItemProcessor processor() { 
		return new FileToExtensionProcessor()
	}
	*/
}
