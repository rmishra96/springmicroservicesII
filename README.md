# springmicroservicesII

## Running Axon Server locally by mounting volumne
docker run --name axonserver -p 8024:8024 -p 8124:8124 -v "/path\docker-data\data":/data 
	-v "/path\docker-data\eventdata":/event-data -v "/path\docker-data\config":/config axoniq/axonserver