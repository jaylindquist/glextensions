package com.bitwiseor.extensions.batch

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component

class DirectoryItemReader implements ItemReader<File>, InitializingBean {
	File directory
	private Iterator<File> iterator;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		assert directory.isDirectory()
		this.iterator = Arrays.asList(directory.listFiles()).iterator()
	}
	
	@Override
	File read() {
		return iterator.hasNext()? iterator.next() : null
	}
}