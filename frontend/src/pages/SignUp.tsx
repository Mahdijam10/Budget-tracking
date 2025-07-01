
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import axios from "axios";
import { useAuth } from "@/AuthContext";

import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card";

const SignUp = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();
    const { login } = useAuth();


    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");

        try {
            await axios.post("http://localhost:8089/api/users", {
                username,
                password,
            });

            login();
            navigate("/");
        } catch (err) {
            console.error(err);
            setError("Signup failed. Please try again.");
        }
    };

    return (
        <div className="min-h-screen bg-gray-50 flex items-center justify-center px-4">
            <Card className="w-full max-w-md">
                <CardHeader className="text-center">
                    <div className="flex justify-center mb-4">
                        <div className="w-12 h-12 bg-green-600 rounded-lg flex items-center justify-center">
                            <span className="text-white font-bold text-xl">$</span>
                        </div>
                    </div>
                    <CardTitle className="text-2xl font-bold">Registration</CardTitle>
                    <CardDescription>Getting started with your Budget Tracker account</CardDescription>
                </CardHeader>
                <CardContent>
                    <form onSubmit={handleSubmit} className="space-y-4">
                        <div className="space-y-2">
                            <Label htmlFor="username">Username</Label>
                            <Input
                                id="username"
                                type="username"
                                placeholder="Enter your username"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                                required
                            />
                        </div>
                        <div className="space-y-2">
                            <Label htmlFor="password">Password</Label>
                            <Input
                                id="password"
                                type="password"
                                placeholder="Enter your password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                        </div>
                        <Button type="submit" className="w-full bg-green-600 hover:bg-green-700">
                            Sign Up
                        </Button>
                    </form>
                    <div className="mt-4 text-center">
                        <Link to="/" className="text-sm text-gray-600 hover:text-gray-800">
                            ← Back to Home
                        </Link>
                    </div>
                </CardContent>
            </Card>
        </div>
    );
};

export default SignUp;
