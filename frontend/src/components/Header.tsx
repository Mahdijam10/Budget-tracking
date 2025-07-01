import { useState } from "react";
import { Link } from "react-router-dom";
import { Menu, X } from "lucide-react";
import { Button } from "@/components/ui/button";
import { useAuth } from "@/AuthContext"; // adjust path if needed

const Header = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const { isAuthenticated } = useAuth();

  return (
    <header className="bg-white shadow-sm sticky top-0 z-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center py-4">
          <Link to="/" className="flex items-center">
            <div className="w-8 h-8 bg-green-600 rounded-lg flex items-center justify-center mr-3">
              <span className="text-white font-bold text-lg">$</span>
            </div>
            <span className="text-xl font-bold text-gray-900">Budget Tracker</span>
          </Link>

          {/* Desktop Navigation */}
          <nav className="hidden md:flex space-x-8">
            <a href="#features" className="text-gray-600 hover:text-green-600 transition-colors">Features</a>
            <a href="#testimonials" className="text-gray-600 hover:text-green-600 transition-colors">Testimonials</a>
            <a href="#contact" className="text-gray-600 hover:text-green-600 transition-colors">Contact</a>
          </nav>

          {/* Desktop CTA */}
          <div className="hidden md:flex space-x-4">
            {!isAuthenticated && (
              <Link to="/signin">
                <Button variant="outline" className="text-green-600 border-green-600 hover:bg-green-50">
                  Sign In
                </Button>
              </Link>
            )}
            <Link to="/dashboard">
              <Button className="bg-green-600 hover:bg-green-700">
                Get Started
              </Button>
            </Link>
          </div>

          {/* Mobile menu button */}
          <button
            className="md:hidden"
            onClick={() => setIsMenuOpen(!isMenuOpen)}
          >
            {isMenuOpen ? <X className="h-6 w-6" /> : <Menu className="h-6 w-6" />}
          </button>
        </div>

        {/* Mobile Navigation */}
        {isMenuOpen && (
          <div className="md:hidden py-4">
            <nav className="flex flex-col space-y-4">
              <a href="#features" className="text-gray-600 hover:text-green-600 transition-colors">Features</a>
              <a href="#testimonials" className="text-gray-600 hover:text-green-600 transition-colors">Testimonials</a>
              <a href="#contact" className="text-gray-600 hover:text-green-600 transition-colors">Contact</a>
              <div className="flex flex-col space-y-2 pt-4">
                {!isAuthenticated && (
                  <Link to="/signin">
                    <Button variant="outline" className="w-full text-green-600 border-green-600 hover:bg-green-50">
                      Sign In
                    </Button>
                  </Link>
                )}
                <Link to="/dashboard">
                  <Button className="w-full bg-green-600 hover:bg-green-700">
                    Get Started
                  </Button>
                </Link>
              </div>
            </nav>
          </div>
        )}
      </div>
    </header>
  );
};

export default Header;
