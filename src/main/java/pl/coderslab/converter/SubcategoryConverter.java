package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.entity.Subcategory;
import pl.coderslab.repository.SubcategoryRepository;

public class SubcategoryConverter implements Converter<String, Subcategory>{

	@Autowired
	private SubcategoryRepository subcategoryRepository;

	@Override
	public Subcategory convert(String source) {
		return subcategoryRepository.findById(Long.parseLong(source));
	}

}
