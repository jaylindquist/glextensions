package com.bitwiseor.extensions.batch

import groovy.util.logging.Slf4j

import java.util.List;

import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggingItemWriter implements ItemWriter {
	@Override
	void write(List items) {
		items.each{ log.info it.toString() }
	}
}