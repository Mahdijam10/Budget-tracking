
import { Calendar, Check, Edit, User, Trash2, ArrowUp } from "lucide-react";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card";

const Features = () => {
  const features = [
    {
      icon: <Edit className="h-8 w-8 text-green-600" />,
      title: "Create and Manage Budgets",
      description: "Define budgets with custom limits, categories, and date ranges. Set up multiple budgets for different purposes and time periods."
    },
    {
      icon: <ArrowUp className="h-8 w-8 text-blue-600" />,
      title: "Track Income and Expenses",
      description: "Add income sources and expenses tied to specific budgets. Get real-time insights into your spending patterns."
    },
    {
      icon: <Calendar className="h-8 w-8 text-purple-600" />,
      title: "View All Budgets",
      description: "Access a comprehensive list of your current and past budgets. Monitor progress and analyze historical data."
    },
    {
      icon: <Check className="h-8 w-8 text-orange-600" />,
      title: "Edit or Delete Budgets",
      description: "Update budget limits, modify categories, or remove outdated budgets. Keep your financial planning current and relevant."
    },
    {
      icon: <User className="h-8 w-8 text-red-600" />,
      title: "User-Focused Design",
      description: "Enjoy a clean, intuitive interface designed for ease of use. Access your budgets on any device with our mobile-friendly design."
    },
    {
      icon: <Trash2 className="h-8 w-8 text-gray-600" />,
      title: "Smart Analytics",
      description: "Get insights into your spending habits with visual charts and reports. Make informed decisions about your financial future."
    }
  ];

  return (
    <section id="features" className="py-20 bg-gray-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-16">
          <h2 className="text-3xl lg:text-4xl font-bold text-gray-900 mb-4">
            Everything You Need to Manage Your Budget
          </h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            Powerful features designed to make budgeting simple, effective, and accessible for everyone.
          </p>
        </div>

        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
          {features.map((feature, index) => (
            <Card key={index} className="hover:shadow-lg transition-shadow duration-300 hover-scale">
              <CardHeader>
                <div className="mb-4">
                  {feature.icon}
                </div>
                <CardTitle className="text-xl font-semibold text-gray-900">
                  {feature.title}
                </CardTitle>
              </CardHeader>
              <CardContent>
                <CardDescription className="text-gray-600 leading-relaxed">
                  {feature.description}
                </CardDescription>
              </CardContent>
            </Card>
          ))}
        </div>
      </div>
    </section>
  );
};

export default Features;
