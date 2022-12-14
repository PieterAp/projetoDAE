Entidades Projeto DAE:

-- BD --

Occurrence (occurrence_id, description)

Repair(repair_id, occurence_id)

-- MockAPI --
	
Clients:

User (user_id (pk nn), name (nn), email (nn), address (nn), phone (nn), user_type (nn)) user_type individual or organization, user_id stores either nif or nipc

InsuranceEntity (insurance_entity_id (pk nn), name (nn), email (nn), address (nn), phone (nn), share_capital (nn))
           
Policy (policy_id (pk nn), client_id (fk nn), insurance_entity_id (fk nn), policy_type (nn))
