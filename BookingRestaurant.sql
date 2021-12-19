

CREATE SCHEMA IF NOT EXISTS `BOOKING_RESTAURANT` DEFAULT CHARACTER SET utf8 ;
USE `BOOKING_RESTAURANT` ;

-- -----------------------------------------------------
-- Table `BOOKING_RESTAURANT`.`RESTAURANT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOKING_RESTAURANT`.`RESTAURANT` (
  `ID` INT(19) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NULL,
  `DESCRIPTION` VARCHAR(100) NULL,
  `ADDRESS` VARCHAR(100) NULL,
  `IMAGE` VARCHAR(500) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BOOKING_RESTAURANT`.`RESERVATION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOKING_RESTAURANT`.`RESERVATION` (
  `ID` INT(19) NOT NULL AUTO_INCREMENT,
  `LOCATOR` VARCHAR(45) NULL,
  `PERSON` INT(19) NULL,
  `DATE` DATE NULL,
  `TURN` VARCHAR(45) NULL,
  `RESTAURANT_ID` INT(19) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_RESERVATION_RESTAURANT_idx` (`RESTAURANT_ID` ASC),
  CONSTRAINT `fk_RESERVATION_RESTAURANT`
    FOREIGN KEY (`RESTAURANT_ID`)
    REFERENCES `BOOKING_RESTAURANT`.`RESTAURANT` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BOOKING_RESTAURANT`.`TURN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOKING_RESTAURANT`.`TURN` (
  `ID` INT(19) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NULL,
  `RESTAURANT_ID` INT(19) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_TURN_RESTAURANT1_idx` (`RESTAURANT_ID` ASC) ,
  CONSTRAINT `fk_TURN_RESTAURANT1`
    FOREIGN KEY (`RESTAURANT_ID`)
    REFERENCES `BOOKING_RESTAURANT`.`RESTAURANT` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BOOKING_RESTAURANT`.`BOARD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOKING_RESTAURANT`.`BOARD` (
  `ID` INT(19) NOT NULL AUTO_INCREMENT,
  `CAPACITY` INT(19) NULL,
  `NUMBER` INT(19) NULL,
  `RESTAURANT_ID` INT(19) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_BOARD_RESTAURANT1_idx` (`RESTAURANT_ID` ASC) ,
  CONSTRAINT `fk_BOARD_RESTAURANT1`
    FOREIGN KEY (`RESTAURANT_ID`)
    REFERENCES `BOOKING_RESTAURANT`.`RESTAURANT` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- INSERT TEST VALUES RESTAURANT
-- -----------------------------------------------------

INSERT INTO `BOOKING_RESTAURANT`.`RESTAURANT` (`ID`, `NAME`, `DESCRIPTION`, `ADDRESS`, `IMAGE`) 
VALUES ('1', 'McDonalds', 'Hamburguesas', 'cr 7 # 6 - 12', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTG112IWS3mhJkk0wE3gPGRb50cEbd2jojy2w&usqp=CAU');
INSERT INTO `BOOKING_RESTAURANT`.`RESTAURANT` (`ID`, `NAME`, `DESCRIPTION`, `ADDRESS`, `IMAGE`) 
VALUES ('2', 'KFC', 'Pollos', 'Plaza central', 'https://media-cdn.tripadvisor.com/media/photo-s/09/83/bc/07/kfc.jpg');
INSERT INTO `BOOKING_RESTAURANT`.`RESTAURANT` (`ID`, `NAME`, `DESCRIPTION`, `ADDRESS`, `IMAGE`) 
VALUES ('3', 'PIZZA HUT', 'Pizza', 'Centro mayor', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRuZ82GADbtSe45_FeY3YpjAYZe1M04hcVQqA&usqp=CAU');

-- -----------------------------------------------------
-- INSERT TEST VALUES TURN
-- -----------------------------------------------------

INSERT INTO `BOOKING_RESTAURANT`.`TURN` (`ID`, `NAME`, `RESTAURANT_ID`) VALUES ('1', 'TURNO_10_00', '1');
INSERT INTO `BOOKING_RESTAURANT`.`TURN` (`ID`, `NAME`, `RESTAURANT_ID`) VALUES ('2', 'TURNO_11_00', '1');
INSERT INTO `BOOKING_RESTAURANT`.`TURN` (`ID`, `NAME`, `RESTAURANT_ID`) VALUES ('3', 'TURNO_12_00', '1');
INSERT INTO `BOOKING_RESTAURANT`.`TURN` (`ID`, `NAME`, `RESTAURANT_ID`) VALUES ('4', 'TURNO_13_00', '1');

INSERT INTO `BOOKING_RESTAURANT`.`TURN` (`ID`, `NAME`, `RESTAURANT_ID`) VALUES ('5', 'TURNO_10_00', '2');
INSERT INTO `BOOKING_RESTAURANT`.`TURN` (`ID`, `NAME`, `RESTAURANT_ID`) VALUES ('6', 'TURNO_11_00', '2');
INSERT INTO `BOOKING_RESTAURANT`.`TURN` (`ID`, `NAME`, `RESTAURANT_ID`) VALUES ('7', 'TURNO_12_00', '2');
INSERT INTO `BOOKING_RESTAURANT`.`TURN` (`ID`, `NAME`, `RESTAURANT_ID`) VALUES ('8', 'TURNO_13_00', '2');

-- -----------------------------------------------------
-- INSERT TEST VALUES BOARD
-- -----------------------------------------------------

INSERT INTO `BOOKING_RESTAURANT`.`BOARD` (`ID`, `CAPACITY`, `NUMBER`, `RESTAURANT_ID`) VALUES ('1', '3', '1', '1');
INSERT INTO `BOOKING_RESTAURANT`.`BOARD` (`ID`, `CAPACITY`, `NUMBER`, `RESTAURANT_ID`) VALUES ('2', '6', '2', '1');
INSERT INTO `BOOKING_RESTAURANT`.`BOARD` (`ID`, `CAPACITY`, `NUMBER`, `RESTAURANT_ID`) VALUES ('3', '2', '3', '1');
INSERT INTO `BOOKING_RESTAURANT`.`BOARD` (`ID`, `CAPACITY`, `NUMBER`, `RESTAURANT_ID`) VALUES ('4', '5', '4', '1');

INSERT INTO `BOOKING_RESTAURANT`.`BOARD` (`ID`, `CAPACITY`, `NUMBER`, `RESTAURANT_ID`) VALUES ('5', '4', '1', '2');
INSERT INTO `BOOKING_RESTAURANT`.`BOARD` (`ID`, `CAPACITY`, `NUMBER`, `RESTAURANT_ID`) VALUES ('6', '6', '2', '2');
INSERT INTO `BOOKING_RESTAURANT`.`BOARD` (`ID`, `CAPACITY`, `NUMBER`, `RESTAURANT_ID`) VALUES ('7', '4', '3', '2');
INSERT INTO `BOOKING_RESTAURANT`.`BOARD` (`ID`, `CAPACITY`, `NUMBER`, `RESTAURANT_ID`) VALUES ('8', '8', '4', '2');

-- -----------------------------------------------------
-- INSERT TEST VALUES RESERVATION
-- -----------------------------------------------------

INSERT INTO `BOOKING_RESTAURANT`.`RESERVATION` (`ID`,`LOCATOR`,`PERSON`,`DATE`,`TURN`,`RESTAURANT_ID`) 
VALUES ('1', 'McDonalds 6', '6', '2021-10-19', 'TURNO_10_00', '1');
INSERT INTO `BOOKING_RESTAURANT`.`RESERVATION` (`ID`,`LOCATOR`,`PERSON`,`DATE`,`TURN`,`RESTAURANT_ID`) 
VALUES ('2', 'kfc 2', '6', '2021-10-20', 'TURNO_11_00', '2');

