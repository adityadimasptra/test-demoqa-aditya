Feature: Books Store

  @Positive
  Scenario: to Search registered book
    Given User go to "https://demoqa.com/books"
    When User in "Book Store" page
    And User search book "Git Pocket Guide"
    And User click book "Git Pocket Guide"
    Then User see book "Git Pocket Guide"

  @Negative
  Scenario: to Search unregistered book
    Given User go to "https://demoqa.com/books"
    When User in "Book Store" page
    And User search book "qa engineer"
    Then User see "No rows found"

