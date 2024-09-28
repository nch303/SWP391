// Sample user
const sampleUser = { name: "John Doe", email: "john@example.com", plan: "Premium" };

// Example usage
<ViewingProfile user={sampleUser} />

function ViewingProfile({ user }) {
    return (
        <div className="profile">
            <div className="column">
                <h1>{user.name}</h1>
                <p>Email: {user.email}</p>
            </div>
            <div className="column">
                <h2>Current Plan: {user.plan}</h2>
            </div>
        </div>
    );
}

export default ViewingProfile; // Added default export

