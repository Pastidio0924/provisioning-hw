-- Insert sample devices
INSERT INTO device (mac_address, username, password, device_type, override_fragment) 
VALUES ('aa-bb-cc-11-22-33', 'john', 'doe', 'DESK', 'domain=sip.anotherdomain.com\nport=5161\ntimeout=10');

INSERT INTO device (mac_address, username, password, device_type, override_fragment) 
VALUES ('dd-ee-ff-44-55-66', 'jane', 'smith', 'CONFERENCE', '{"domain": "sip.anotherdomain.com", "port": "5161", "timeout": 10}');

-- Insert devices without override fragments
INSERT INTO device (mac_address, username, password, device_type) 
VALUES ('11-22-33-aa-bb-cc', 'alice', 'brown', 'DESK');

INSERT INTO device (mac_address, username, password, device_type) 
VALUES ('44-55-66-dd-ee-ff', 'bob', 'wilson', 'CONFERENCE');
