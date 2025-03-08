
import React, { useEffect, useRef, useState } from 'react';
import { Receipt, Search, ArrowUpDown } from 'lucide-react';
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { 
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";

const vaccines = [
  { id: 1, name: 'Vaccine 6 trong 1', type: 'Bắt buộc', price: '1.200.000 VNĐ', ages: '2, 3, 4 tháng' },
  { id: 2, name: 'Vaccine Rotavirus', type: 'Khuyến nghị', price: '800.000 VNĐ', ages: '2, 4, 6 tháng' },
  { id: 3, name: 'Vaccine Pneumococcal', type: 'Bắt buộc', price: '1.100.000 VNĐ', ages: '2, 4, 12 tháng' },
  { id: 4, name: 'Vaccine Viêm não Nhật Bản', type: 'Bắt buộc', price: '750.000 VNĐ', ages: '12, 13 tháng' },
  { id: 5, name: 'Vaccine HPV', type: 'Khuyến nghị', price: '1.500.000 VNĐ', ages: '9-14 tuổi' },
  { id: 6, name: 'Vaccine Thủy đậu', type: 'Khuyến nghị', price: '850.000 VNĐ', ages: '12-15 tháng' },
  { id: 7, name: 'Vaccine Cúm', type: 'Theo mùa', price: '400.000 VNĐ', ages: 'Mọi lứa tuổi' },
  { id: 8, name: 'Vaccine Viêm gan A', type: 'Khuyến nghị', price: '650.000 VNĐ', ages: '12, 18 tháng' },
];

const PriceList = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [filteredVaccines, setFilteredVaccines] = useState(vaccines);
  const [sortConfig, setSortConfig] = useState<{ key: string | null, direction: 'ascending' | 'descending' | null }>({
    key: null,
    direction: null
  });
  
  const sectionRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const observer = new IntersectionObserver(
      ([entry]) => {
        if (entry.isIntersecting) {
          entry.target.classList.add('opacity-100');
          entry.target.classList.remove('opacity-0');
        }
      },
      { threshold: 0.1 }
    );

    const currentSectionRef = sectionRef.current;
    if (currentSectionRef) {
      observer.observe(currentSectionRef);
    }

    return () => {
      if (currentSectionRef) {
        observer.unobserve(currentSectionRef);
      }
    };
  }, []);

  useEffect(() => {
    const results = vaccines.filter(vaccine =>
      vaccine.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
      vaccine.type.toLowerCase().includes(searchTerm.toLowerCase()) ||
      vaccine.ages.toLowerCase().includes(searchTerm.toLowerCase())
    );
    
    setFilteredVaccines(results);
  }, [searchTerm]);

  const requestSort = (key: string) => {
    let direction: 'ascending' | 'descending' | null = 'ascending';
    
    if (sortConfig.key === key) {
      if (sortConfig.direction === 'ascending') {
        direction = 'descending';
      } else if (sortConfig.direction === 'descending') {
        direction = null;
      }
    }
    
    setSortConfig({ key, direction });
    
    if (direction === null) {
      setFilteredVaccines(vaccines.filter(vaccine =>
        vaccine.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
        vaccine.type.toLowerCase().includes(searchTerm.toLowerCase()) ||
        vaccine.ages.toLowerCase().includes(searchTerm.toLowerCase())
      ));
      return;
    }
    
    const sortedVaccines = [...filteredVaccines].sort((a, b) => {
      if (a[key as keyof typeof a] < b[key as keyof typeof b]) {
        return direction === 'ascending' ? -1 : 1;
      }
      if (a[key as keyof typeof a] > b[key as keyof typeof b]) {
        return direction === 'ascending' ? 1 : -1;
      }
      return 0;
    });
    
    setFilteredVaccines(sortedVaccines);
  };

  return (
    <section id="prices" className="py-20 bg-gradient-to-b from-white to-blue-50">
      <div 
        ref={sectionRef}
        className="section-container opacity-0 transition-opacity duration-1000"
      >
        <div className="text-center mb-16">
          <div className="inline-flex items-center rounded-full px-3 py-1 text-sm font-medium bg-blue-50 text-primary mb-6">
            <Receipt className="h-4 w-4 mr-1" />
            <span>Bảng giá</span>
          </div>
          <h2 className="text-balance font-medium mb-6">
            <span className="text-primary">Bảng giá</span> các loại vaccine
          </h2>
          <p className="text-gray-600 max-w-xl mx-auto">
            Chúng tôi cung cấp các loại vaccine với mức giá cạnh tranh và cam kết chất lượng.
            Dưới đây là bảng giá các loại vaccine phổ biến.
          </p>
        </div>

        <div className="glass rounded-xl p-6 mb-8">
          <div className="flex flex-col sm:flex-row items-center justify-between mb-6 gap-4">
            <div className="relative w-full sm:w-64">
              <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 h-4 w-4" />
              <Input
                placeholder="Tìm kiếm vaccine..."
                className="pl-10"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
              />
            </div>
            <Button variant="outline" className="w-full sm:w-auto">
              Xem bảng giá đầy đủ
            </Button>
          </div>

          <div className="rounded-md border overflow-x-auto">
            <Table>
              <TableHeader>
                <TableRow>
                  <TableHead className="w-[50px]">STT</TableHead>
                  <TableHead className="min-w-[180px] cursor-pointer" onClick={() => requestSort('name')}>
                    <div className="flex items-center">
                      Tên vaccine
                      <ArrowUpDown className="ml-2 h-4 w-4" />
                    </div>
                  </TableHead>
                  <TableHead className="cursor-pointer" onClick={() => requestSort('type')}>
                    <div className="flex items-center">
                      Loại
                      <ArrowUpDown className="ml-2 h-4 w-4" />
                    </div>
                  </TableHead>
                  <TableHead className="cursor-pointer" onClick={() => requestSort('price')}>
                    <div className="flex items-center">
                      Giá tiền
                      <ArrowUpDown className="ml-2 h-4 w-4" />
                    </div>
                  </TableHead>
                  <TableHead className="hidden sm:table-cell">Độ tuổi khuyến nghị</TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                {filteredVaccines.map((vaccine, index) => (
                  <TableRow key={vaccine.id}>
                    <TableCell className="font-medium">{index + 1}</TableCell>
                    <TableCell>{vaccine.name}</TableCell>
                    <TableCell>
                      <span className={`px-2 py-1 rounded-full text-xs font-medium ${
                        vaccine.type === 'Bắt buộc' 
                          ? 'bg-blue-100 text-blue-700' 
                          : vaccine.type === 'Khuyến nghị'
                          ? 'bg-green-100 text-green-700'
                          : 'bg-orange-100 text-orange-700'
                      }`}>
                        {vaccine.type}
                      </span>
                    </TableCell>
                    <TableCell>{vaccine.price}</TableCell>
                    <TableCell className="hidden sm:table-cell">{vaccine.ages}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </div>
        </div>

        <div className="text-center">
          <p className="text-gray-500 mb-4">* Giá có thể thay đổi theo thời điểm. Vui lòng liên hệ trực tiếp để có thông tin chính xác nhất.</p>
          <Button className="bg-primary hover:bg-primary/90 text-white btn-hover-effect">
            Tư vấn gói tiêm phù hợp
          </Button>
        </div>
      </div>
    </section>
  );
};

export default PriceList;
