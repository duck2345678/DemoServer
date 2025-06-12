CREATE TABLE AgentType (
    agentTypeID SERIAL PRIMARY KEY,
    agentTypeName VARCHAR(255) NOT NULL,
    maximumDebt INTEGER NOT NULL
);
