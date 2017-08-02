Feature: Messaging

  Scenario: Adding a message
    Given the message "Hello world" already exists
    When I add the message "TestEE.fi rocks"
    Then the following messages should be available
      | Hello world     |
      | TestEE.fi rocks |

  Scenario: Publishing on twitter
    When I add the message "TestEE.fi rocks"
    Then the message "TestEE.fi rocks" was published to twitter
    And no other message was published to twitter
