--
-- Datenbank: `doctest`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `commented`
--

CREATE TABLE IF NOT EXISTS `commented` (
  `id` int(11) NOT NULL COMMENT 'id column comment',
  `name` varchar(55) default 'default' COMMENT 'name column comment',
  `date` date default NULL COMMENT 'date column comment',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table level comment';

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `indexed01`
--

CREATE TABLE IF NOT EXISTS `indexed01` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(55) NOT NULL,
  `date` date NOT NULL,
  `indexed02_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `date` (`date`),
  KEY `indexed02_id` (`indexed02_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `indexed02`
--

CREATE TABLE IF NOT EXISTS `indexed02` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(55) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `triggers`
--

CREATE TABLE IF NOT EXISTS `triggers` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id column comment',
  `before` int(11) NOT NULL COMMENT 'before column comment',
  `after` int(11) NOT NULL COMMENT 'after column comment',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='triggers table comment' AUTO_INCREMENT=1 ;

--
-- Trigger `triggers`
--
DROP TRIGGER IF EXISTS `doctest`.`testtrigger`;
DELIMITER //
CREATE TRIGGER `doctest`.`testtrigger` BEFORE UPDATE ON `doctest`.`triggers`
 FOR EACH ROW SET NEW.before = NEW.before + 1
//
DELIMITER ;
DROP TRIGGER IF EXISTS `doctest`.`testtrigger2`;
DELIMITER //
CREATE TRIGGER `doctest`.`testtrigger2` AFTER UPDATE ON `doctest`.`triggers`
 FOR EACH ROW UPDATE triggers SET after = after + 1 WHERE id = NEW.id
//
DELIMITER ;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `indexed01`
--
ALTER TABLE `indexed01`
  ADD CONSTRAINT `indexed01_ibfk_1` FOREIGN KEY (`indexed02_id`) REFERENCES `indexed02` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE FUNCTION `testproc`() RETURNS TINYINT COMMENT "procedure comment" RETURN 1