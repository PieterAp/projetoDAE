Branch login

Entidades Projeto DAE:

-- BD --

Occurrence (occurrence_id, description)

Repair(repair_id, occurence_id)

-- MockAPI --
	
Clients:

User (user_id (pk nn), name (nn), email (nn), address (nn), phone (nn), user_type (nn)) user_type individual, organization, insurance_entity, expert; user_id stores either nif or nipc

InsuranceEntity (insurance_entity_id (pk nn), name (nn), email (nn), address (nn), phone (nn), share_capital (nn))
           
Policy (policy_id (pk nn), client_id (fk nn), insurance_entity_id (fk nn), policy_type (nn))

Funcionamento:

User individual: 

Sees his/her occurences and personal account information.

User organization:

Sees his/her occurences and personal account information.

User insurance_entity:

Sees occurences of its clients and manages them (approve the occurence or not)

User expert:

Validates an occurence for an insurance entity (if the occurence is legit (possible damage AI detection here))

User repair_service:

Updates the status of its reparations
