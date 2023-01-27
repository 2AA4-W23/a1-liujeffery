# A1 - Piraten Karpen

  * Author: < Jeffery Liu >
  * Email: < liu1121@mcmaster.ca >

## Build and Execution

  * To clean your working directory:
    * `mvn clean`
  * To compile the project:
    * `mvn compile`
  * To run the project in development mode:
    * `mvn -q exec:java` (here, `-q` tells maven to be _quiet_)
  * To package the project as a turn-key artefact:
    * `mvn package`
  * To run the packaged delivery:
    * `java -jar target/piraten-karpen-jar-with-dependencies.jar` 

Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

 * Status: 
   * Pending (P), Started (S), Blocked (B), Done (D)
 * Definition of Done (DoD):
   * If after five different tests, the feature is still working as intended. Each scenario should test the feature in a different way and handle edge cases.

### Backlog 

| MVP? | Id  | Feature  | Status  |  Started  | Delivered |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
| x   | F01 | Roll a dice | D | 01/01/23 | 26/01/23 |
| x   | F02 | Roll eight dice | D | 26/01/23 | 26/01/23 |
| x   | F03 | Compute number of golds and diamonds | D | 26/01/23 | 26/01/23 |
| x   | F04 | End of game with three skulls | D | 26/01/23 | 26/01/23 |
| x   | F05 | Player keeps on rerolling until turn ends | D | 26/01/23 | 26/01/23 |
| x   | F06 | Player keeping random dice at their turn | S | 26/01/23 | 
| x   | F07 | Repeat game 42 times, print out result| P | | 
| ... | ... | ... |

