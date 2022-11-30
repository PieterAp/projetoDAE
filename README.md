Entidades Projeto DAE:

BD:

Clients:

Ocurrences:

Occurrence (occurrence_id, description)

MockAPI:
	
Clients:

Individual (individual_id, nif, name, email, address, phone)

Organization (organization_id, nipc, name, email, address, phone, organization_type)

	                             fk nif null     fk nipc null
Policy (policy_id, policy_coverage, individual_id, organization_id, vehicle_id)

Vehicle (vehicle_id, vehicle_type)
