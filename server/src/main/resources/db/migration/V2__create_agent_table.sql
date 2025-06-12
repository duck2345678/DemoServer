CREATE TABLE IF NOT EXISTS agent (
    agentid SERIAL PRIMARY KEY, -- tự động tăng
    agentname VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    agenttypeid INTEGER,
    districtid INTEGER,
    receptiondate TIMESTAMP,
    debtmoney INTEGER,

    CONSTRAINT fk_agenttype FOREIGN KEY (agenttypeid) REFERENCES agent_type(agenttypeid),
    CONSTRAINT fk_district FOREIGN KEY (districtid) REFERENCES district(districtid)
);
