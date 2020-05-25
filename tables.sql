CREATE TABLE `clients` (
    `id` INTEGER NOT NULL,
	`lastName` VARCHAR(255) NOT NULL,
	`firstName` VARCHAR(255) NOT NULL,
	`age` INTEGER  NOT NULL,
	`hasSubscription` BOOLEAN NOT NULL DEFAULT FALSE,
	PRIMARY KEY (`id`)
);
CREATE TABLE `movies` (
    `type` VARCHAR(255) NOT NULL,
    `id` INTEGER NOT NULL,
	`duration` INTEGER NOT NULL,
    `movieName` VARCHAR(255) NOT NULL,
    `nrTickets` INTEGER NOT NULL,
    `recommendedAge` INTEGER NOT NULL,
    `soldTickets` INTEGER NOT NULL ,
    `requiresSupervizor` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (`movieName`)
);

CREATE TABLE `soldTickets` (
    `movieName` VARCHAR(255) NOT NULL,
    `clientName` VARCHAR(255) NOT NULL,
    `price`DOUBLE NOT NULL
);