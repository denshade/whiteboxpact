CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE productEnvironment (
    product INT,
    environment VARCHAR(255) NOT NULL,
    version VARCHAR(50) NOT NULL,
    FOREIGN KEY (product) REFERENCES product(id),
    PRIMARY KEY (product, environment)
);

CREATE TABLE pact (
    id INT AUTO_INCREMENT PRIMARY KEY,
    creationDate TIMESTAMP NOT NULL,
    payload TEXT NOT NULL
);

CREATE TABLE supportedPact (
    pactId INT,
    product INT,
    version VARCHAR(50) NOT NULL,
    verifiedAt TIMESTAMP NOT NULL,
    FOREIGN KEY (pactId) REFERENCES pact(id),
    FOREIGN KEY (product) REFERENCES product(id),
    PRIMARY KEY (pactId, product)
);

