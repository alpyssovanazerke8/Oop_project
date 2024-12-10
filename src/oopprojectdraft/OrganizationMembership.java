package oopprojectdraft;

public class OrganizationMembership {
	private Organization organization;
    private String role;

    public OrganizationMembership(Organization organization, String role) {
        this.organization = organization;
        this.role = role;
    }

    @Override
    public String toString() {
        return organization.getName() + " (" + role + ")";
    }

}
