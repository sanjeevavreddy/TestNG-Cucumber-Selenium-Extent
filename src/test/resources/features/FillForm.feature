Feature: Fill Form Feature

  Scenario: Fill Form Scenario 1
    Given user launch the url
    Then user enter UserName and Password as "Sanjeev" and "Reddy"

  Scenario Outline: Fill Form Scenario 2
    Given user launch the url
    Then user enter UserName and Password as "<User>" and "<Password>"

    Examples:
      | User    | Password |
      | Sanjeev | Reddy    |