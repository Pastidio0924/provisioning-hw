#!/bin/bash
echo '--- Desk phone without override fragment ---'
curl -sX GET http://localhost:8081/api/v1/provisioning/aa-bb-cc-dd-ee-ff
echo '--- Conference phone without override fragment ---'
curl -sX GET http://localhost:8081/api/v1/provisioning/f1-e2-d3-c4-b5-a6 | jq '.'
echo '--- Desk phone with override fragment ---'
curl -sX GET http://localhost:8081/api/v1/provisioning/a1-b2-c3-d4-e5-f6
echo '--- Conference phone with override fragment ---'
curl -sX GET http://localhost:8081/api/v1/provisioning/1a-2b-3c-4d-5e-6f | jq '.'
echo '--- Not existing device ---'
curl -w "%{http_code}" -sX GET http://localhost:8081/api/v1/provisioning/aa-aa-aa-aa-aa-aa
