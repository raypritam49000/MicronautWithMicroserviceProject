package pl.piomin.services.organization.repository;

import jakarta.inject.Singleton;
import pl.piomin.services.organization.model.Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class OrganizationRepository {

	private List<Organization> organizations = new ArrayList<>();
	
	public Organization add(Organization organization) {
		organization.setId((long) (organizations.size()+1));
		organizations.add(organization);
		return organization;
	}
	
	public Organization findById(Long id) {
		Optional<Organization> organization = organizations.stream().filter(a -> a.getId().equals(id)).findFirst();
		return organization.orElse(null);
	}
	
	public List<Organization> findAll() {
		return organizations;
	}
	
}
