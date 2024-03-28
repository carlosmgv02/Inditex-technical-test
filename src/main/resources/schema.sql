DROP TABLE IF EXISTS prices;

CREATE TABLE prices (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        brand_id INT NOT NULL,
                        start_date TIMESTAMP NOT NULL,
                        end_date TIMESTAMP NOT NULL,
                        price_list INT NOT NULL,
                        product_id BIGINT NOT NULL,
                        priority INT NOT NULL,
                        price DECIMAL(19,2) NOT NULL,
                        curr CHAR(3) NOT NULL
);
