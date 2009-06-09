/* Copyright (c) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.google.gdata.model.gd;

import com.google.gdata.model.AttributeKey;
import com.google.gdata.model.DefaultRegistry;
import com.google.gdata.model.Element;
import com.google.gdata.model.ElementCreator;
import com.google.gdata.model.ElementKey;
import com.google.gdata.model.QName;
import com.google.gdata.util.Namespaces;

/**
 * Describes the base set of values for event visibility.  Similar to CLASS in
 * RFC 2445.
 *
 * 
 */
public class Visibility extends Element {

  /** Value. */
  public static final class Value {

    /** Allow some readers to see the event. */
    public static final String CONFIDENTIAL = Namespaces.gPrefix +
        "event.confidential";

    /** Inherit the behavior from the preferences of the owner of the calendar
     * on which the event appears. */
    public static final String DEFAULT = Namespaces.gPrefix + "event.default";

    /** Allow fewest readers to see the event. */
    public static final String PRIVATE = Namespaces.gPrefix + "event.private";

    /** Allow most readers to see the event. */
    public static final String PUBLIC = Namespaces.gPrefix + "event.public";

    /** Array containing all available values. */
    private static final String[] ALL_VALUES = {
      CONFIDENTIAL,
      DEFAULT,
      PRIVATE,
      PUBLIC};

    /** Returns an array of all values defined in this class. */
    public static String[] values() {
      return ALL_VALUES;
    }

    private Value() {}
  }

  /**
   * The key for this element.
   */
  public static final ElementKey<Void,
      Visibility> KEY = ElementKey.of(new QName(Namespaces.gNs, "visibility"),
      Void.class, Visibility.class);

  /**
   * Value.
   */
  public static final AttributeKey<String> VALUE = AttributeKey.of(new
      QName(null, "value"), String.class);

  /*
   * Generate the default metadata for this element.
   */
  static {
    ElementCreator builder = DefaultRegistry.build(KEY);
    builder.addAttribute(VALUE).setRequired(true);
  }

  /**
   * Default mutable constructor.
   */
  public Visibility() {
    this(KEY);
  }

  /**
   * Create an instance using a different key.
   */
  public Visibility(ElementKey<Void, ? extends Visibility> key) {
    super(key);
  }

  /**
   * Constructs a new instance by doing a shallow copy of data from an existing
   * {@link Element} instance. Will use the given {@link ElementKey} as the key
   * for the element.
   *
   * @param key The key to use for this element.
   * @param source source element
   */
  public Visibility(ElementKey<Void, ? extends Visibility> key,
      Element source) {
    super(key, source);
  }

   @Override
   public Visibility lock() {
     return (Visibility) super.lock();
   }

  /**
   * Returns the value.
   *
   * @return value
   */
  public String getValue() {
    return super.getAttributeValue(VALUE);
  }

  /**
   * Sets the value.
   *
   * @param value value or <code>null</code> to reset
   */
  public Visibility setValue(String value) {
    super.setAttributeValue(VALUE, value);
    return this;
  }

  /**
   * Returns whether it has the value.
   *
   * @return whether it has the value
   */
  public boolean hasValue() {
    return getValue() != null;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!sameClassAs(obj)) {
      return false;
    }
    Visibility other = (Visibility) obj;
    return eq(getValue(), other.getValue());
  }

  @Override
  public int hashCode() {
    int result = getClass().hashCode();
    if (getValue() != null) {
      result = 37 * result + getValue().hashCode();
    }
    return result;
  }

  @Override
  public String toString() {
    return "{Visibility value=" + getAttributeValue(VALUE) + "}";
  }

}
