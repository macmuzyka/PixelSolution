Query results available under these two endpoints:

Results is a sum of a visits to a given practitioner, or if param is missing or equal to ALL
result is sum of all visits to all the practitioners
visits/practitioner={param}

Result is a sum of a visits to a given practitioner based on a city, or multiple cities. 
Again, missing param or input ALL queries all the records from all practitioners or all the cities.
visits/patients/city={params,separated,by,coma}&practitioner={param}