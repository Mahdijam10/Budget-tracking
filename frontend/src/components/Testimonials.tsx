
import { Card, CardContent } from "@/components/ui/card";

const Testimonials = () => {
  const testimonials = [
    {
      name: "Serkan Oray",
      role: "Team Member",
      content: "I'm passionate about creating tools that simplify everyday life. Outside of work, I enjoy hiking and exploring new technologies in fintech.", avatar: "SO"
    },
    {
      name: "Mahdi Jamshidi",
      role: "Team Member",
      content: "With a strong background in computer science, I thrive on finding insights in numbers. In my free time, I love working on side projects and playing chess.", avatar: "MJ"
    },

  ];

  return (
    <section id="testimonials" className="py-20 bg-white">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-16">
          <h2 className="text-3xl lg:text-4xl font-bold text-gray-900 mb-4">
            Our Team
          </h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            Meet the dedicated individuals behind our budget tracking project, committed to making financial management simple and effective.          </p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-8 justify-center">

          {testimonials.map((testimonial, index) => (
            <Card key={index} className="hover:shadow-lg transition-shadow duration-300">
              <CardContent className="p-6">
                <div className="flex items-center mb-4">
                  <div className="w-12 h-12 bg-green-600 rounded-full flex items-center justify-center text-white font-semibold mr-4">
                    {testimonial.avatar}
                  </div>
                  <div>
                    <h4 className="font-semibold text-gray-900">{testimonial.name}</h4>
                    <p className="text-sm text-gray-600">{testimonial.role}</p>
                  </div>
                </div>
                <p className="text-gray-700 leading-relaxed italic">
                  "{testimonial.content}"
                </p>
              </CardContent>
            </Card>
          ))}
        </div>
      </div>
    </section>
  );
};

export default Testimonials;
