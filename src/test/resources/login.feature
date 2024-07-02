Feature: Login DemoBlaze

  @test
  Scenario: Admin customer authenticate to DemoBlaze
    Given customer navigates to Demo Blaze
    When customer authenticate to the store
      | username | admin |
      | password | admin |
    Then customer should authenticate to the store successfully

  @test2
  Scenario: New customer sign up to Demo Blaze
    Given customer navigates to Demo Blaze
    When customer sign up to the store
      | username | boss1     |
      | password | sunflowr@ |
    Then customer should see alert  message "Sign up successful."
    
  @test3
  Scenario: Admin customer purchase phone, laptop and monitor products
    Given customer navigates to Demo Blaze
    When customer authenticate to the store
      | username | admin |
      | password | admin |
    And customer add "Phones" as "Samsung galaxy s7"
    Then customer should see alert  message of added product
    And customer add "Laptops" as "MacBook Pro"
    Then customer should see alert  message of added product
    And customer add "Monitors" as "Apple monitor 24"
    Then customer should see alert  message of added product
    And customer open cart
    Then customer should see product added to cart
     | Item                                | Price | 
      | Samsung galaxy s7 |  800   |
      | MacBook Pro              | 1100 |
      | Apple monitor 24      |  400   |
      | Total                                 | 2300 |
    When customer place order
      | Name           | Sawasdee         |
      | Country        | Thailand           |
      | City                 | Bangkok          | 
      | Credit card | 1203451133998801 |
      | Month           |               01 |
      | Year                |               27 |
 Then customer should see purchase confirmation
      | Amount              | 2300 |
      | Card Number | 1203451133998801 |
      | Name                  | Sawasdee |
      
        @test4
  Scenario: Admin customer purchase phone, laptop and monitor products
    Given customer navigates to Demo Blaze
    When customer authenticate to the store
      | username | admin |
      | password | admin |
    And customer add "Phones" as "Nexus 6"
    Then customer should see alert  message of added product
    And customer add "Phones" as "HTC One M9"
    Then customer should see alert  message of added product
    And customer add "Phones" as "Iphone 6 32gb"
    Then customer should see alert  message of added product
    And customer open cart
    Then customer should see product added to cart
     | Item                                | Price | 
      | Nexus 6                          |  650   |
      | HTC One M9                | 700 |
      | Iphone 6 32gb             |  790   |
      | Total                                 | 2140 |
    When customer place order
      | Name           | Aloha Hawaii         |
      | Country        | Thailand           |
      | City                 | Bangkok          | 
      | Credit card | 667099123231 |
      | Month           |               06 |
      | Year                |               28 |
 Then customer should see purchase confirmation
      | Amount              | 2140 |
      | Card Number | 667099123231 |
      | Name                  | Aloha Hawaii |