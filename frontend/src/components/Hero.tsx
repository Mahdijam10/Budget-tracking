import { Button } from "@/components/ui/button";
import { ArrowDown } from "lucide-react";
import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

const Hero = () => {
  const [budgets, setBudgets] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8089/api/budgets")
      .then((response) => {
        setBudgets(response.data);
      })
      .catch((error) => {
        console.error("Error fetching budgets:", error);
      });
  }, []);

  const totalLeft = budgets.reduce((acc, b) => {
    const income = b.incomeDTOs?.reduce((sum, i) => sum + parseFloat(i.amount), 0) || 0;
    const expense = b.expenseDTOs?.reduce((sum, e) => sum + parseFloat(e.amount), 0) || 0;
    return acc + (income - expense);
  }, 0);

  return (
    <section className="relative bg-gradient-to-br from-green-50 to-blue-50 py-20 lg:py-32">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid lg:grid-cols-2 gap-12 items-center">
          {/* Text Section */}
          <div className="animate-fade-in">
            <h1 className="text-4xl lg:text-6xl font-bold text-gray-900 mb-6">
              Take Control of Your
              <span className="text-green-600 block">Financial Future</span>
            </h1>
            <p className="text-xl text-gray-600 mb-8 leading-relaxed">
              Budget Tracker helps you manage your money with confidence. Track expenses,
              set budgets, and achieve your financial goals with our intuitive platform.
            </p>
            <div className="flex flex-col sm:flex-row gap-4">
              <Link to="/createbudget">
                <Button size="lg" className="bg-green-600 hover:bg-green-700 px-8 py-4 text-lg">
                  Create Your First Budget
                </Button>
              </Link>
              <Button
                variant="outline"
                size="lg"
                className="border-green-600 text-green-600 hover:bg-green-50 px-8 py-4 text-lg"
              >
                Watch Demo
              </Button>
            </div>
            <div className="mt-8 flex items-center space-x-6 text-sm text-gray-500">
              <div className="flex items-center">
                <span className="w-2 h-2 bg-green-600 rounded-full mr-2"></span>
                Free to start
              </div>
              <div className="flex items-center">
                <span className="w-2 h-2 bg-green-600 rounded-full mr-2"></span>
                No credit card required
              </div>
            </div>
          </div>

          {/* Budget Overview Card */}
          <div className="lg:pl-12 animate-fade-in">
            <div className="bg-white rounded-2xl shadow-2xl p-8 transform hover:scale-105 transition-transform duration-300">
              <div className="space-y-6">
                <div className="flex justify-between items-center">
                  <h3 className="text-lg font-semibold text-gray-900">Monthly Budget Overview</h3>
                  <span className="text-green-600 font-medium">
                    ${totalLeft.toFixed(2)} left
                  </span>
                </div>

                <div className="space-y-4">
                  {budgets.map((budget) => {
                    const income = budget.incomeDTOs?.reduce((sum, i) => sum + parseFloat(i.amount), 0) || 0;
                    const expense = budget.expenseDTOs?.reduce((sum, e) => sum + parseFloat(e.amount), 0) || 0;
                    const incomePercent = income > 0 ? Math.min((income / (income + expense)) * 100, 100) : 0;
                    const expensePercent = income + expense > 0 ? Math.min((expense / (income + expense)) * 100, 100) : 0;

                    return (
                      <div key={budget.id} className="bg-gray-50 rounded-lg p-4">
                        <div className="mb-2">
                          <div className="mb-2">
                            <div className="flex justify-between items-center">
                              <h4 className="text-md font-semibold text-gray-800">{budget.name}</h4>
                              <span className="text-gray-900 font-medium">
                                +${income.toFixed(2)} / -${expense.toFixed(2)}
                              </span>
                            </div>
                            <p className="text-sm text-gray-500">{budget.category}</p>
                          </div>
                        </div>
                        <div className="relative w-full bg-gray-200 rounded-full h-2 overflow-hidden">
                          <div
                            className="absolute left-0 top-0 h-2 bg-green-500 rounded-full"
                            style={{ width: `${incomePercent}%` }}
                          ></div>
                          <div
                            className="absolute right-0 top-0 h-2 bg-red-500 rounded-full"
                            style={{ width: `${expensePercent}%` }}
                          ></div>
                        </div>
                      </div>

                    );
                  })}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Bouncing Arrow */}
      <div className="absolute bottom-8 left-1/2 transform -translate-x-1/2 animate-bounce">
        <ArrowDown className="h-6 w-6 text-gray-400" />
      </div>
    </section>
  );
};

export default Hero;
