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


package com.google.gdata.data.finance;

import com.google.gdata.data.ExtensionDescription;
import com.google.gdata.data.ExtensionPoint;
import com.google.gdata.data.ExtensionProfile;
import com.google.gdata.data.extensions.Money;

import java.util.List;

/**
 * Total gain for the portfolio or position.
 *
 * 
 */
@ExtensionDescription.Default(
    nsAlias = FinanceNamespace.GF_ALIAS,
    nsUri = FinanceNamespace.GF,
    localName = Gain.XML_NAME)
public class Gain extends ExtensionPoint {

  /** XML element name */
  static final String XML_NAME = "gain";

  /**
   * Default mutable constructor.
   */
  public Gain() {
    super();
  }

  @Override
  public void declareExtensions(ExtensionProfile extProfile) {
    if (extProfile.isDeclared(Gain.class)) {
      return;
    }
    extProfile.declare(Gain.class, Money.getDefaultDescription(true, true));
  }

  /**
   * Returns the monetary value of the total gain.
   *
   * @return monetary value of the total gain
   */
  public List<Money> getMoney() {
    return getRepeatingExtension(Money.class);
  }

  /**
   * Adds a new monetary value of the total gain.
   *
   * @param money monetary value of the total gain
   */
  public void addMoney(Money money) {
    getMoney().add(money);
  }

  /**
   * Returns whether it has the monetary value of the total gain.
   *
   * @return whether it has the monetary value of the total gain
   */
  public boolean hasMoney() {
    return hasRepeatingExtension(Money.class);
  }

  @Override
  protected void validate() {
  }

  /**
   * Returns the extension description, specifying whether it is required, and
   * whether it is repeatable.
   *
   * @param required   whether it is required
   * @param repeatable whether it is repeatable
   * @return extension description
   */
  public static ExtensionDescription getDefaultDescription(boolean required,
      boolean repeatable) {
    ExtensionDescription desc =
        ExtensionDescription.getDefaultDescription(Gain.class);
    desc.setRequired(required);
    desc.setRepeatable(repeatable);
    return desc;
  }

  @Override
  public String toString() {
    return "{Gain}";
  }

}
