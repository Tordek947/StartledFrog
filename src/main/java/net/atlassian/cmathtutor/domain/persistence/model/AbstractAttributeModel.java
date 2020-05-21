package net.atlassian.cmathtutor.domain.persistence.model;

import javax.xml.bind.annotation.XmlTransient;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.atlassian.cmathtutor.domain.persistence.Attribute;

@ToString(callSuper = true)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public abstract class AbstractAttributeModel extends AbstractIdentifyableModel implements Attribute {

    @EqualsAndHashCode.Include
    private StringProperty name = new SimpleStringProperty();
    @ToString.Exclude
    @EqualsAndHashCode.Include
    private ObjectProperty<PersistenceUnitModel> parentClassifier = new SimpleObjectProperty<>();

    public AbstractAttributeModel(String id) {
	super(id);
    }

    @Override
    public StringProperty nameProperty() {
	return this.name;
    }

    @Override
    public String getName() {
	return this.nameProperty().get();
    }

    public void setName(final String name) {
	this.nameProperty().set(name);
    }

    public ReadOnlyObjectProperty<PersistenceUnitModel> parentClassifierProperty() {
	return this.parentClassifier;
    }

    @XmlTransient
    public PersistenceUnitModel getParentClassifier() {
	return this.parentClassifierProperty().get();
    }

    public void setParentClassifier(final PersistenceUnitModel parentClassifier) {
	this.parentClassifier.set(parentClassifier);
    }
}
