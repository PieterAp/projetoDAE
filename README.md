Entidades Projeto DAE:

-- BD --

Occurrence (occurrence_id, description)

-- MockAPI --
	
Clients:

User (user_id (pk nn), nif (n), nipc (n), name (nn), email (nn), address (nn), phone (nn), organization_type (n))

InsuranceEntity (insurance_entity_id (pk nn), name (nn), email (nn), address (nn), phone (nn), share_capital (nn))
           
Policy (policy_id (pk nn), client_id (fk nn), insurance_entity_id (fk nn), policy_type (nn))
