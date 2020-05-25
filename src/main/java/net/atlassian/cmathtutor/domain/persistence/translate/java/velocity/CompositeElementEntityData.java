package net.atlassian.cmathtutor.domain.persistence.translate.java.velocity;

import java.util.Collections;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import net.atlassian.cmathtutor.domain.persistence.translate.java.instance.AnnotationInstance;
import net.atlassian.cmathtutor.domain.persistence.translate.java.instance.AnnotationInstances;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class CompositeElementEntityData extends EntityData {

    private static final List<AnnotationInstance<?>> COMPOSITE_PARENT_ANNOTATIONS = Collections
	    .singletonList(AnnotationInstances.jsonIgnore());

    private VariableData compositeParentField;

    public CompositeElementEntityData(String name, String packageName, String tableName) {
	super(name, packageName, tableName);
    }

    public void selectCompositeParentField(String parentFieldName) {
	compositeParentField = getFields().stream().filter(field -> field.getName().equals(parentFieldName)).findAny()
		.orElseThrow(() -> new IllegalArgumentException(
			"Entity " + getName() + " doesn't contain field with name " + parentFieldName));
    }

    public List<AnnotationInstance<?>> getCompositeParentAnnotations() {
	return COMPOSITE_PARENT_ANNOTATIONS;
    }

    public String getCompositeParentGetterName() {
	return composeGetterName(compositeParentField);
    }

    public static String composeGetterName(@NonNull VariableData field) {
	String fieldName = field.getName();
	return "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
    }

}
