CREATE DATABASE `aulaweb` /*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE TABLE `Vaga` (
  `id` INT NOT NULL,
  `status` TINYINT NULL,
  PRIMARY KEY (`id`))
 ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
 
CREATE TABLE `Funcionario` (
  `id` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  `tel` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
   ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
   
   
    CREATE TABLE `cliente` (
  `id` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  `tel` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
 ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
 
 CREATE TABLE `carro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `placa` VARCHAR(45) NULL,
  `nome` VARCHAR(45) NULL,
  `Cliente_id` INT NOT NULL,
  PRIMARY KEY (`id`),
 
  FOREIGN KEY (`Cliente_id`) REFERENCES cliente(`id`)
   
 ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
 
 
 CREATE TABLE`Historico` (
  	`Carro_id` INT NOT NULL,
  	`Carro_Cliente_id` INT NOT NULL,
  	`Vaga_id` INT NOT NULL,
  	`horas`TIME NULL,
  	`data` DATE NULL,
    `Funcionario_id` INT NOT NULL,
    
  
  	PRIMARY KEY (`Carro_id`,`data`),
    
  	FOREIGN KEY (`Carro_id`)REFERENCES carro(`id`),
  	FOREIGN KEY (`Carro_Cliente_id`)REFERENCES cliente(`id`),
  	FOREIGN KEY (`Vaga_id`) REFERENCES vaga(`id`),
    FOREIGN KEY (`Funcionario_id`) REFERENCES funcionario(`id`)   
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

