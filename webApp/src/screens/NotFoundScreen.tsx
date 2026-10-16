import { Link } from "@tanstack/react-router";

export function NotFoundScreen() {
	return (
		<main>
			<h1>404</h1>
			<h2>Page not found</h2>
			<p>The page you are looking for doesn't exist or may have been moved</p>
			<Link to="/">Go back home</Link>
		</main>
	);
}
