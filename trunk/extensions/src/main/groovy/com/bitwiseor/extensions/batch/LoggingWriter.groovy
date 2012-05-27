package com.bitwiseor.extensions.batch

import groovy.util.logging.Slf4j

import java.util.List;

import org.springframework.batch.item.ItemWriter

@Slf4j
public class LoggingWriter implements ItemWriter {
	@Override
	void write(List items) {
		items.each{ log.info it.toString() }
	}
}