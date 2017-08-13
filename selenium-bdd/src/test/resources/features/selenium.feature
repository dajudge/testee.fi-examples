Feature: Selenium testing

  Background:
    Given I open /static/index.html

  Scenario Outline: Adding items
    When I enter "<Item name>" into the itemToAdd field
    And I click the "Add" button
    Then the table should contain a row with value "<Item name>"
    Examples:
      | Item name   |
      | Potatoes    |
      | Olive oil   |
      | Pork steaks |
      | Lager beer  |
      | BBQ sauce   |

  Scenario: Multiple items
    When I add the following items
      | Potatoes    |
      | Olive oil   |
      | Pork steaks |
      | Lager beer  |
      | BBQ sauce   |
    Then the table should contain 5 items

  Scenario: Not adding empty items
    When I enter "" into the itemToAdd field
    And I click the "Add" button
    Then the table should contain 0 items

  Scenario: Removing items
    Given I add the following items
      | Potatoes    |
      | Olive oil   |
    When I click the checkmark button of the "Potatoes" item
    Then the table should contain 1 items