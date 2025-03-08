
import React, { useEffect, useRef } from 'react';
import { Calendar, Users, Clock, ArrowRight } from 'lucide-react';
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { 
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";

const BookingSection = () => {
  const sectionRef = useRef<HTMLDivElement>(null);
  const formRef = useRef<HTMLDivElement>(null);
  const imageRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const observer = new IntersectionObserver(
      (entries) => {
        entries.forEach((entry) => {
          if (entry.isIntersecting) {
            entry.target.classList.add('opacity-100');
            entry.target.classList.remove('opacity-0');
          }
        });
      },
      { threshold: 0.1 }
    );

    const currentRefs = [sectionRef.current, formRef.current, imageRef.current].filter(Boolean);
    
    currentRefs.forEach(ref => {
      if (ref) {
        observer.observe(ref);
      }
    });

    return () => {
      currentRefs.forEach(ref => {
        if (ref) {
          observer.unobserve(ref);
        }
      });
    };
  }, []);

  return (
    <section className="py-20 bg-white">
      <div 
        ref={sectionRef}
        className="section-container opacity-0 transition-opacity duration-1000"
      >
        <div className="text-center mb-16">
          <div className="inline-flex items-center rounded-full px-3 py-1 text-sm font-medium bg-blue-50 text-primary mb-6">
            <Calendar className="h-4 w-4 mr-1" />
            <span>Book Your Appointment</span>
          </div>
          <h2 className="text-balance font-medium mb-6">
            Schedule Your <span className="text-primary">Vaccination</span> Today
          </h2>
          <p className="text-gray-600 max-w-xl mx-auto">
            Book an appointment at one of our convenient locations. Our easy booking process 
            ensures you can get vaccinated quickly and efficiently.
          </p>
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-2 gap-12 items-center">
          <div 
            ref={formRef}
            className="glass rounded-xl p-8 opacity-0 transition-opacity duration-1000 delay-200"
          >
            <h3 className="text-2xl font-medium mb-6">Book an Appointment</h3>
            <form className="space-y-6">
              <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div className="space-y-2">
                  <Label htmlFor="firstName">First Name</Label>
                  <Input id="firstName" placeholder="Enter your first name" />
                </div>
                <div className="space-y-2">
                  <Label htmlFor="lastName">Last Name</Label>
                  <Input id="lastName" placeholder="Enter your last name" />
                </div>
              </div>

              <div className="space-y-2">
                <Label htmlFor="email">Email</Label>
                <Input id="email" type="email" placeholder="Enter your email" />
              </div>

              <div className="space-y-2">
                <Label htmlFor="phone">Phone Number</Label>
                <Input id="phone" placeholder="Enter your phone number" />
              </div>

              <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div className="space-y-2">
                  <Label htmlFor="vaccineType">Vaccine Type</Label>
                  <Select>
                    <SelectTrigger>
                      <SelectValue placeholder="Select vaccine type" />
                    </SelectTrigger>
                    <SelectContent>
                      <SelectItem value="covid">COVID-19</SelectItem>
                      <SelectItem value="flu">Flu</SelectItem>
                      <SelectItem value="hepatitis">Hepatitis</SelectItem>
                      <SelectItem value="other">Other</SelectItem>
                    </SelectContent>
                  </Select>
                </div>
                <div className="space-y-2">
                  <Label htmlFor="location">Location</Label>
                  <Select>
                    <SelectTrigger>
                      <SelectValue placeholder="Select location" />
                    </SelectTrigger>
                    <SelectContent>
                      <SelectItem value="central">Central Clinic</SelectItem>
                      <SelectItem value="westside">Westside Center</SelectItem>
                      <SelectItem value="eastside">Eastside Hub</SelectItem>
                    </SelectContent>
                  </Select>
                </div>
              </div>

              <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div className="space-y-2">
                  <Label htmlFor="date">Preferred Date</Label>
                  <Input id="date" type="date" />
                </div>
                <div className="space-y-2">
                  <Label htmlFor="time">Preferred Time</Label>
                  <Select>
                    <SelectTrigger>
                      <SelectValue placeholder="Select time" />
                    </SelectTrigger>
                    <SelectContent>
                      <SelectItem value="morning">Morning (9AM - 12PM)</SelectItem>
                      <SelectItem value="afternoon">Afternoon (12PM - 3PM)</SelectItem>
                      <SelectItem value="evening">Evening (3PM - 6PM)</SelectItem>
                    </SelectContent>
                  </Select>
                </div>
              </div>

              <Button className="w-full bg-primary hover:bg-primary/90 text-white btn-hover-effect">
                Book Appointment
                <ArrowRight className="ml-2 h-4 w-4" />
              </Button>
            </form>
          </div>

          <div 
            ref={imageRef}
            className="relative opacity-0 transition-opacity duration-1000 delay-300"
          >
            <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 w-80 h-80 bg-primary/10 rounded-full filter blur-3xl animate-pulse-slow"></div>
            <div className="relative">
              <div className="glass rounded-xl p-8 mb-6">
                <div className="flex items-start mb-6">
                  <div className="bg-blue-50 rounded-full p-3 mr-4">
                    <Users className="h-6 w-6 text-primary" />
                  </div>
                  <div>
                    <h4 className="font-medium mb-1">Expert Healthcare Professionals</h4>
                    <p className="text-gray-600">Our team consists of certified healthcare providers specialized in vaccination.</p>
                  </div>
                </div>
                <div className="flex items-start">
                  <div className="bg-blue-50 rounded-full p-3 mr-4">
                    <Clock className="h-6 w-6 text-primary" />
                  </div>
                  <div>
                    <h4 className="font-medium mb-1">Quick & Efficient Process</h4>
                    <p className="text-gray-600">Our streamlined process ensures minimal waiting time and maximum safety.</p>
                  </div>
                </div>
              </div>

              <div className="glass rounded-xl p-6">
                <h3 className="text-xl font-medium mb-4">Why Choose Our Vaccination Service?</h3>
                <ul className="space-y-3">
                  {[
                    'State-of-the-art facilities',
                    'Comprehensive vaccine options',
                    'Experienced medical staff',
                    'Convenient locations',
                    'Flexible scheduling',
                    'Follow-up care'
                  ].map((item, i) => (
                    <li key={i} className="flex items-center">
                      <div className="bg-green-50 rounded-full p-1 mr-3">
                        <svg className="h-4 w-4 text-green-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 13l4 4L19 7" />
                        </svg>
                      </div>
                      <span className="text-gray-600">{item}</span>
                    </li>
                  ))}
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default BookingSection;
