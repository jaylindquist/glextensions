package com.bitwiseor.extensions.batch

import org.springframework.batch.item.ItemReader
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class DirectoryItemReader implements ItemReader<File>, InitializingBean {
	@Value('D:/code/glextensions')
	File directory
	private Iterator<File> iterator;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		assert directory.isDirectory()
		this.iterator = directory.listFiles().findAll { it.name.startsWith('c') }.iterator()
	}
	
	@Override
	File read() {
		return iterator.hasNext()? iterator.next() : null
	}
}