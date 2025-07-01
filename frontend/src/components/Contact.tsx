
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card";

const Contact = () => {
  return (
    <section id="contact" className="py-20 bg-gradient-to-br from-green-600 to-blue-600">
      <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
        <div className="mb-12">
          <h2 className="text-3xl lg:text-4xl font-bold text-white mb-4">
            Ready to Take Control of Your Finances?
          </h2>
          <p className="text-xl text-green-100 max-w-2xl mx-auto">
            Join thousands of users who have already started their journey to financial freedom.
          </p>
        </div>

        <Card className="max-w-lg mx-auto bg-white/95 backdrop-blur">
          <CardHeader>
            <CardTitle className="text-2xl text-gray-900">Get Started Today</CardTitle>
            <CardDescription className="text-gray-600">
              Create your first budget in less than 5 minutes
            </CardDescription>
          </CardHeader>
          <CardContent className="space-y-4">
            <Button 
              size="lg" 
              className="w-full bg-green-600 hover:bg-green-700 py-4 text-lg font-semibold"
            >
              Create Your First Budget
            </Button>
            <p className="text-sm text-gray-500">
              No credit card required • Free to start • Cancel anytime
            </p>
          </CardContent>
        </Card>

        <div className="mt-12 text-center">
          <p className="text-green-100 mb-4">Questions? We're here to help.</p>
          <div className="flex flex-col sm:flex-row gap-4 justify-center">
            <Button variant="outline" className="bg-white/10 border-white/20 text-white hover:bg-white/20">
              Contact Support
            </Button>
            <Button variant="outline" className="bg-white/10 border-white/20 text-white hover:bg-white/20">
              Schedule Demo
            </Button>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Contact;
