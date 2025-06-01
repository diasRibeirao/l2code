CREATE TABLE caixas (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    altura DECIMAL(10,2) NOT NULL,
    largura DECIMAL(10,2) NOT NULL,
    comprimento DECIMAL(10,2) NOT NULL,
    CONSTRAINT tamanhos_uk UNIQUE (altura, largura, comprimento)
);
