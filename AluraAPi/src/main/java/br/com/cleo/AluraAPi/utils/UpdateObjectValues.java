package br.com.cleo.AluraAPi.utils;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class UpdateObjectValues extends BeanUtils {

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);

        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    /**
     * @param source Object that contains the new updated values
     * @param target Object that will be updated
     */
    public void updateObject(Object source, Object target) {
        copyProperties(source, target, getNullPropertyNames(source));
    }

}
