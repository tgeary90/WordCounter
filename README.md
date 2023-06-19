
# Word Counter

## Description

Small lib and play app to add words to a store and return their count

## Design Principles

1. Used a data structure that supports fast store and fetch operations. ie. a map. The design is modular. Operations were decomposed into functions and utilised using 2 abstraction layers. Add at layer 1, then addWord and safeAdd at the lower level. This provides for extensibility and readability
3. Dependency injection was used to support ease of testing. eg. The Translater was injected as a mock
4. memory usage should be small. Each instance of the word was not stored, only an incremental count
5. The word count domain (WordCounter library) is agnostic of its execution environment (play framework). This is an instance of an onion style architecture. Core domain with access layer on the outside.

## Implementation and Hosting Considerations

1. The solution could be hosted in the cloud as a lambda in AWS or microservice in a kubernetes pod. Or simply as a service running on an EC2 instance. Similar form factors exist for GCP and Azure
2. Resiliency would be achieved through hosting multiple instances of the service - either in a kubernetes pod or running load-balanced EC2 instances.


