#Energy Tracker

#Getting Started

                            +--------+
                            | Client |
                            +--------+
                                |
                                v
                          +-------------+
                          | API Gateway |
                          +-------------+
                                |
                    --------------------------
                    |                        | 
                    v                        v
             +------------+          +----------------+
             | Device     |          | User Service   |
             | Service    |          +----------------+
             +------------+                  ^
                    |                        |
                    v                        |
                  +-------+                  |
                  | MySQL |<-----------------+
                  +-------+
                       ^
                       |
                  +----------------+
                  | Ingestion      |
                  | Service        |
                  +----------------+
                       |
                       v
                     +-----+
                     |Kafka |
                     +-----+
                       |
                       v
                 +----------------+
                 | Usage Service  |
                 +----------------+
                       |
                       v
                 +----------------+
                 | TimeSeries DB  |
                 +----------------+
                       |
                       v
                     +-----+
                     |Kafka |
                     +-----+
                       |
                       v
                 +----------------+
                 | Alert Service  |
                 +----------------+
                       ^
                       |
                +----------------+
                | Insight Service|
                +----------------+
                       |
                       v
                       +----> AI


### Architecture Flow Explanation:

1. **Client / IoT Devices**: The users or IoT devices send data and requests through the **API Gateway**. This gateway handles authentication, routing, and request management.

2. **API Gateway**: Acts as the entry point for all requests. It forwards requests to the appropriate services:

    * **Device Service**: Manages device metadata and persists it to **MySQL**.
    * **User Service**: Handles user-related data and communicates with MySQL.
    * **Ingestion Service**: Receives IoT streaming data and pushes it to **Kafka**.

3. **Kafka**: Acts as a message broker for decoupling services and handling high-throughput streaming data.

4. **Usage Service**: Consumes data from Kafka, processes it, and stores time-series data in **TimeSeries DB** for analytics.

5. **Insight Service**: Uses the processed data from Usage Service to generate insights and may leverage AI for predictive analytics.

6. **Alert Service**: Consumes messages from Kafka to trigger alerts based on thresholds or anomalies, potentially feeding back into MySQL or notifying users.

7. **AI Integration**: The Insight Service feeds data to AI components for advanced analytics, predictions, and intelligent decision-making.

This architecture separates responsibilities, ensures scalability, and allows real-time processing with Kafka while maintaining persistence in MySQL and TimeSeries DB.

